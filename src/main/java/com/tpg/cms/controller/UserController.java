package com.tpg.cms.controller;

import com.tpg.cms.dao.UserDao;
import com.tpg.cms.model.SysUser;
import com.tpg.cms.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
    @RequestMapping("/users")
    public class UserController {

        private static final Logger log = LoggerFactory.getLogger("adminLogger");

//        @Autowired
//        private UserService userService;
//        @Autowired
//        private UserDao userDao;
//
//       // @LogAnnotation
//        @PostMapping
//        @ApiOperation(value = "保存用户")
//        @PreAuthorize("hasAuthority('sys:user:add')")
//        public SysUser saveUser() {
//
//        }
//
//
//        @PutMapping
//        @ApiOperation(value = "修改用户")
//        @PreAuthorize("hasAuthority('sys:user:add')")
//        public SysUser updateUser(@RequestBody ) {
//
//        }
//
//
//        @PutMapping(params = "advatar")
//        @ApiOperation(value = "修改头像")
//        public void updateHeadImgUrl(String advatar) {
//
//        }
//
//
//        @PutMapping("/{username}")
//        @ApiOperation(value = "修改密码")
//        @PreAuthorize("hasAuthority('sys:user:password')")
//        public void changePassword(@PathVariable String username, String oldPassword, String newPassword) {
//
//        }
//
//        @GetMapping
//        @ApiOperation(value = "用户列表")
//        @PreAuthorize("hasAuthority('sys:user:query')")
//        public PageTableResponse listUsers(PageTableRequest request) {
//            return new PageTableHandler(new CountHandler() {
//
//                @Override
//                public int count(PageTableRequest request) {
//                    return userDao.count(request.getParams());
//                }
//            }, new ListHandler() {
//
//                @Override
//                public List<SysUser> list(PageTableRequest request) {
//                    List<SysUser> list = userDao.list(request.getParams(), request.getOffset(), request.getLimit());
//                    return list;
//                }
//            }).handle(request);
//        }
//
//        @ApiOperation(value = "当前登录用户")
//        @GetMapping("/current")
//        public SysUser currentUser() {
//
//        }
//
//        @ApiOperation(value = "根据用户id获取用户")
//        @GetMapping("/{id}")
//        @PreAuthorize("hasAuthority('sys:user:query')")
//        public SysUser user(@PathVariable Long id) {
//            return userDao.getById(id);
//        }
//
//    }
}
