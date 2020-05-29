package com.tpg.cms.controller;

import com.github.pagehelper.PageInfo;
import com.tpg.cms.config.MD5;
import com.tpg.cms.model.SysUser;
import com.tpg.cms.service.SyRoleService;
import com.tpg.cms.service.SysUserService;
import com.tpg.cms.utils.Result;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/admin/acl/user")
@CrossOrigin
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SyRoleService syRoleService;

    @ApiOperation(value = "获取管理用户分页列表")
    @GetMapping("{page}/{limit}")
    public Result index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Integer page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Integer limit,

            @ApiParam(name = "courseQuery", value = "查询对象", required = false)
                    SysUser userQuery) {
        PageInfo<SysUser> pageParam = sysUserService.getByPage(page, limit, userQuery.getUsername());

        return Result.OK().data("items", pageParam.getList()).data("total", pageParam.getTotal());
    }

    @ApiOperation(value = "新增管理用户")
    @PostMapping("save")
    public Result save(@RequestBody SysUser user) {
        user.setPassword(MD5.encrypt(user.getPassword()));
        sysUserService.save(user);
        return Result.OK();
    }

    @ApiOperation(value = "修改管理用户")
    @PutMapping("update")
    public Result updateById(@RequestBody SysUser user) {
        sysUserService.updateById(user);
        return Result.OK();
    }

    @ApiOperation(value = "删除管理用户")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable String id) {
        sysUserService.removeById(id);
        return Result.OK();
    }

    @ApiOperation(value = "根据id列表删除管理用户")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<String> idList) {
        sysUserService.removeByIds(idList);
        return Result.OK();
    }

    @ApiOperation(value = "获取用户")
    @GetMapping("get/{id}")
    public Result get(@PathVariable String id) {
        SysUser user = sysUserService.getById(id);
        return Result.OK().data("item", user);
    }

    @ApiOperation(value = "根据用户获取角色数据")
    @GetMapping("/toAssign/{userId}")
    public Result toAssign(@PathVariable String userId) {
        Map<String, Object> roleMap = syRoleService.findRoleByUserId(userId);
        return Result.OK().data(roleMap);
    }

    @ApiOperation(value = "根据用户分配角色")
    @PostMapping("/doAssign")
    public Result doAssign(@RequestParam String userId, @RequestParam String[] roleId) {
        syRoleService.saveUserRoleRealtionShip(userId,roleId);
        return Result.OK();
    }
}

