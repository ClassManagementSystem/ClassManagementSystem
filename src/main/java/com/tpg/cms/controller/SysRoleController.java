package com.tpg.cms.controller;


import com.github.pagehelper.PageInfo;
import com.tpg.cms.model.SysRole;
import com.tpg.cms.service.SyRoleService;
import com.tpg.cms.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 */
@RestController
@RequestMapping("/admin/acl/role")
@CrossOrigin
@Api(tags = "角色管理")
public class SysRoleController {

    @Autowired
    private SyRoleService syRoleService;

    @ApiOperation(value = "获取角色分页列表")
    @GetMapping("{page}/{limit}")
    public Result index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Integer page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Integer limit,
            SysRole role) {

        PageInfo<SysRole> pageParam = syRoleService.getByPage(page, limit, role.getRoleName());

        return Result.OK().data("items", pageParam.getList()).data("total", pageParam.getTotal());
    }

    @ApiOperation(value = "获取角色")
    @GetMapping("get/{id}")
    public Result get(@PathVariable String id) {
        SysRole role = syRoleService.getById(id);
        return Result.OK().data("item", role);
    }

    @ApiOperation(value = "新增角色")
    @PostMapping("save")
    public Result save(@RequestBody SysRole role) {
        syRoleService.save(role);
        return Result.OK();
    }

    @ApiOperation(value = "修改角色")
    @PutMapping("update")
    public Result updateById(@RequestBody SysRole role) {
        syRoleService.updateById(role);
        return Result.OK();
    }

    @ApiOperation(value = "删除角色")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable String id) {
        syRoleService.removeById(id);
        return Result.OK();
    }

    @ApiOperation(value = "根据id列表删除角色")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<String> idList) {
        syRoleService.removeByIds(idList);
        return Result.OK();
    }
}

