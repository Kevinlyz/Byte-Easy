package com.mbyte.easy.sys.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mbyte.easy.common.controller.BaseController;
import com.mbyte.easy.sys.service.IUserService;
import com.mbyte.easy.sys.entity.User;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.message.common.BaseResponse;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 王震
 * @since 2019-03-12
 */
@RestController
@RequestMapping("/sys/user")
public class UserController extends BaseController {



    private IUserService targetService;

    @Autowired
    public UserController(IUserService targetService){
        this.targetService = targetService;
    }


    /**
     * 获取数据列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public BaseResponse findListByPage(@RequestParam(name = "page", defaultValue = "1") int pageIndex,@RequestParam(name = "rows", defaultValue = "20") int step){
        Page page = new Page(pageIndex,step);
        targetService.selectPage(page);
        return BaseResponse.onSuccess(page);
    }


    /**
     * 获取全部数据
     */
    @RequestMapping("/all")
    @ResponseBody
    public BaseResponse findAll(){
        List<User> models = targetService.selectList(null);
        return BaseResponse.onSuccess(models);
    }


    /**
     * 根据ID查找数据
     */
    @RequestMapping("/find")
    @ResponseBody
    public BaseResponse find(@RequestParam("id") Long id){
        User User = targetService.selectById(id);
        if(User==null){
            return BaseResponse.onFail("尚未查询到此ID");
        }
        return BaseResponse.onSuccess(User);
    }


    /**
     * 添加数据
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse addItem(@RequestBody User User){
        boolean isOk = targetService.insert(User);
        if(isOk){
            return BaseResponse.onSuccess("数据添加成功！");
        }
        return BaseResponse.onFail("数据添加失败");
    }


    /**
     * 更新数据
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse updateItem(@RequestBody User User){
        boolean isOk = targetService.updateAllColumnById(User);
        if(isOk){
            return BaseResponse.onSuccess("数据更改成功！");
        }
        return BaseResponse.onFail("数据更改失败");
    }


    /**
     * 删除数据
     */
    @RequestMapping("/del")
    @ResponseBody
    public BaseResponse deleteItems(@RequestParam("ids") List<Long> ids){
        boolean isOk = targetService.deleteBatchIds(ids);
        if(isOk){
            return BaseResponse.onSuccess("数据删除成功！");
        }
            return BaseResponse.onFail("数据删除失败");
        }
    }

