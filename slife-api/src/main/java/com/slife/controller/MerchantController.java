package com.slife.controller;

import com.slife.base.controller.BaseController;
import com.slife.base.entity.ReturnDTO;
import com.slife.base.vo.DataTable;
import com.slife.model.entity.Merchant;
import com.slife.service.IMerchantService;
import com.slife.util.ReturnDTOUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import java.util.List;

/**
 * @author tod
 * @date 2018/1/2
 * <p>
 * Email will_tao@126.com
 * <p>
 * Describe: merchant商家controller
 */
@Controller
@RequestMapping("/api/merchant")
public class MerchantController extends BaseController {

    @Autowired
    private IMerchantService merchantService;

    @ApiOperation(value = "获取商家列表数据", notes = "获取商家列表数据:使用约定的DataTable")
    @PostMapping(value = "/list")
    @ResponseBody
    public DataTable<Merchant> list(@RequestBody DataTable dt, ServletRequest request) {
        return merchantService.pageSearch(dt);
    }

    @ApiOperation(value = "批量删除商家记录", notes = "批量删除商家记录传入商家ids")
    @PostMapping(value = "/delete")
    @ResponseBody
    public ReturnDTO delete(@RequestParam("ids") List<Long> ids, ServletRequest request) {
        boolean success = merchantService.deleteBatchIds(ids);
        if (success) {
            return ReturnDTOUtil.success();
        }
        return ReturnDTOUtil.fail();

    }


}
