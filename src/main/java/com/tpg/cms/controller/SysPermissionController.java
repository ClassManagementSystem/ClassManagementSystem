package com.tpg.cms.controller;


import com.tpg.cms.model.SysPermission;
import com.tpg.cms.service.PermissionService;
import com.tpg.cms.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 权限 菜单管理
 * </p>
 */
@RestController
@RequestMapping("/admin/acl/permission")
@CrossOrigin
@Api(tags = "菜单管理")
public class SysPermissionController {

    @Autowired
    private PermissionService permissionService;

    //获取全部菜单
    @ApiOperation(value = "查询所有菜单")
    @GetMapping
    public Result indexAllPermission() {
        List<SysPermission> list =  permissionService.queryAllMenu();
        return Result.OK().data("children",list);
    }

    @ApiOperation(value = "递归删除菜单")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable String id) {
        permissionService.removeChildById(id);
        return Result.OK();
    }

    @ApiOperation(value = "给角色分配权限")
    @PostMapping("/doAssign")
    public Result doAssign(String roleId, String[] permissionId) {
        permissionService.saveRolePermissionRealtionShip(roleId,permissionId);
        return Result.OK();
    }

    @ApiOperation(value = "根据角色获取菜单")
    @GetMapping("toAssign/{roleId}")
    public Result toAssign(@PathVariable String roleId) {
        List<SysPermission> list = permissionService.selectAllMenu(roleId);
        System.out.println(list);
        return Result.OK().data("children", list);
    }



    @ApiOperation(value = "新增菜单")
    @PostMapping("save")
    public Result save(@RequestBody SysPermission permission) {
        permissionService.save(permission);
        return Result.OK();
    }

    @ApiOperation(value = "修改菜单")
    @PutMapping("update")
    public Result updateById(@RequestBody SysPermission permission) {
        permissionService.updateById(permission);
        return Result.OK();
    }

}

