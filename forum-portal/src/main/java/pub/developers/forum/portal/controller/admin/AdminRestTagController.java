package pub.developers.forum.portal.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pub.developers.forum.api.model.PageRequestModel;
import pub.developers.forum.api.model.PageResponseModel;
import pub.developers.forum.api.model.ResultModel;
import pub.developers.forum.api.request.AdminBooleanRequest;
import pub.developers.forum.api.request.tag.TagCreateRequest;
import pub.developers.forum.api.request.tag.TagPageRequest;
import pub.developers.forum.api.response.tag.TagPageResponse;
import pub.developers.forum.api.service.TagApiService;
import pub.developers.forum.common.constant.Constant;
import pub.developers.forum.portal.support.WebUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Qiangqiang.Bian
 * @create 2020/10/31
 * @desc
 **/
@RestController
@RequestMapping("/admin-rest/tag")
@Api(tags = "管理员管理标签")
public class AdminRestTagController {

    @Resource
    private TagApiService tagApiService;

    @PostMapping("/page")
    @ApiOperation("标签列表（分页）")
    public ResultModel<PageResponseModel<TagPageResponse>> page(@RequestBody PageRequestModel<TagPageRequest> pageRequestModel
            , HttpServletRequest request) {
        request.setAttribute(Constant.REQUEST_HEADER_TOKEN_KEY, WebUtil.cookieGetSid(request));

        return tagApiService.page(pageRequestModel);
    }

    @PostMapping("/audit-state")
    @ApiOperation("管理员标签审核")
    public ResultModel auditState(@RequestBody AdminBooleanRequest booleanRequest, HttpServletRequest request) {
        request.setAttribute(Constant.REQUEST_HEADER_TOKEN_KEY, WebUtil.cookieGetSid(request));

        return tagApiService.auditState(booleanRequest);
    }

    @PostMapping("/add")
    @ApiOperation("管理员添加标签")
    public ResultModel add(@RequestBody TagCreateRequest request) {
        return tagApiService.create(request);
    }

}
