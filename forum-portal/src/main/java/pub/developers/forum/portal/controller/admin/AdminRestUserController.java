package pub.developers.forum.portal.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import pub.developers.forum.api.model.PageRequestModel;
import pub.developers.forum.api.model.PageResponseModel;
import pub.developers.forum.api.model.ResultModel;
import pub.developers.forum.api.request.AdminBooleanRequest;
import pub.developers.forum.api.request.user.UserAdminPageRequest;
import pub.developers.forum.api.request.user.UserOptLogPageRequest;
import pub.developers.forum.api.response.user.UserOptLogPageResponse;
import pub.developers.forum.api.response.user.UserPageResponse;
import pub.developers.forum.api.service.UserApiService;
import pub.developers.forum.common.constant.Constant;
import pub.developers.forum.portal.support.WebUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Qiangqiang.Bian
 * @create 2020/12/8
 * @desc
 **/
@RestController
@RequestMapping("/admin-rest/user")
@Api(tags = "�û�����")

public class AdminRestUserController {

    @Resource
    private UserApiService userApiService;

    @PostMapping("/page")
    @ApiOperation("�����û�")
    public ResultModel<PageResponseModel<UserPageResponse>> add(@RequestBody PageRequestModel<UserAdminPageRequest> pageRequestModel
            , HttpServletRequest request) {
        request.setAttribute(Constant.REQUEST_HEADER_TOKEN_KEY, WebUtil.cookieGetSid(request));

        return userApiService.adminPage(pageRequestModel);
    }

    @PostMapping("/enable/{uid}")
    @ApiOperation("�û�ʹ��")
    public ResultModel enable(@PathVariable Long uid, HttpServletRequest request) {
        request.setAttribute(Constant.REQUEST_HEADER_TOKEN_KEY, WebUtil.cookieGetSid(request));

        return userApiService.enable(uid);
    }

    @PostMapping("/disable/{uid}")
    @ApiOperation("�û����÷�ʹ��")
    public ResultModel disable(@PathVariable Long uid, HttpServletRequest request) {
        request.setAttribute(Constant.REQUEST_HEADER_TOKEN_KEY, WebUtil.cookieGetSid(request));

        return userApiService.disable(uid);
    }

    @PostMapping("/page-opt-log")
    @ApiOperation("��ѯ���²�����¼")
    public ResultModel<PageResponseModel<UserOptLogPageResponse>> pageOptLog(@RequestBody PageRequestModel<UserOptLogPageRequest> pageRequestModel
            , HttpServletRequest request) {
        request.setAttribute(Constant.REQUEST_HEADER_TOKEN_KEY, WebUtil.cookieGetSid(request));

        return userApiService.pageOptLog(pageRequestModel);
    }

    @PostMapping("/update-role")
    @ApiOperation("�û����ý�ɫ")
    public ResultModel updateRole(@RequestBody AdminBooleanRequest booleanRequest
            , HttpServletRequest request) {
        request.setAttribute(Constant.REQUEST_HEADER_TOKEN_KEY, WebUtil.cookieGetSid(request));

        return userApiService.updateRole(booleanRequest);
    }
}
