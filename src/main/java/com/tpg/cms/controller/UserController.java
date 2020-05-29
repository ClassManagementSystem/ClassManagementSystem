package com.tpg.cms.controller;
import com.tpg.cms.model.ClmsUser;
import com.tpg.cms.service.UserService;
import com.tpg.cms.utils.Page;
import com.tpg.cms.utils.ResultAQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: cms
 * @description: 用户表现层
 * @author: lx
 * @create: 2020-05-28 10:56
 **/
@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 根据用户id获取用户信息
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public ResultAQ<ClmsUser> get(@PathVariable("id") Integer id){
        ClmsUser user = userService.getById(id);
        System.out.println(user);
        return new ResultAQ<ClmsUser>(user);
    }

    /**
     * 添加用户
     * @param clmsUser
     * @return
     */
    @PostMapping("/save")
    public ResultAQ<Object> save(@RequestBody ClmsUser clmsUser){
        boolean flag = userService.save(clmsUser);
        if(flag)
            return new ResultAQ<>("保存成功!");
        return new ResultAQ<>("保存失败!");
    }

    /**
     * 根据用户id删除用户
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResultAQ<Object> delete(@PathVariable("id") Integer id){
        boolean flag = userService.deleteById(id);
        if(flag)
            return new ResultAQ<>("删除成功!");
        return new ResultAQ<>("删除失败!");
    }

    /**
     * 更新此用户的信息
     * @param user
     * @return
     */
    @PutMapping("/update")
    public ResultAQ<Object> update(@RequestBody ClmsUser user){
        boolean flag = userService.update(user);
        if(flag)
            return new ResultAQ<>("修改成功!");
        return new ResultAQ<>("修改失败!");
    }

    /**
     * 分页查询用户信息
     * @param page
     * @return
     */
    @PostMapping("/userPage")
    public ResultAQ<Page<ClmsUser>> userPage(@RequestBody Page<ClmsUser> page){
        page = userService.getByPage(page);
        return new ResultAQ<>(page);
    }
}
