package pub.developers.forum.portal.controller.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pub.developers.forum.api.model.ResultModel;
import pub.developers.forum.api.response.tag.TagQueryResponse;
import pub.developers.forum.api.service.TagApiService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Qiangqiang.Bian
 * @create 2020/11/13
 * @desc
 **/
@RestController
@RequestMapping("/tag-rest")
@Api(tags = "标签管理")
public class TagRestController {

    @Resource
    private TagApiService tagApiService;

    @PostMapping("/all")
    @ApiOperation("返回所有标签")
    public ResultModel<List<TagQueryResponse>> all() {
        return tagApiService.queryAll();
    }
}
