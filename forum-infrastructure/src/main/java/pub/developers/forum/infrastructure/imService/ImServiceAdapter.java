package pub.developers.forum.infrastructure.imService;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pub.developers.forum.api.model.ResultModel;
import pub.developers.forum.infrastructure.imService.cmd.UserIn;
import pub.developers.forum.infrastructure.imService.query.PoaAccountInfo;
import pub.developers.forum.infrastructure.imService.query.TokenBody;

@FeignClient(url = "${im.server.url}", name = "imService")
@Api(tags = "im后端系统接口")
public interface ImServiceAdapter {

    @PostMapping("/v1/login/sysUserSignIn")
    @ApiOperation("登录")
    String getUserSignIn(@RequestBody UserIn usrIn);


    @PostMapping("/v1/server/getUserInfoByUserToken")
    @ApiOperation("通过token获取用户信息")
    ResultModel<Object> getUserInfoByToken(@RequestBody TokenBody token);


    @PostMapping("/v1/server/isLegalToken")
    @ApiOperation("校验token是否合法")
    ResultModel<Object> checkTokenIsValid(@RequestBody TokenBody token);

    @PostMapping("/v1/server/getNameByPoaNumber")
    @ApiOperation("根据poaNumber获取对应名称WIP")
    ResultModel<Object> checkTokenIsValid(@RequestBody PoaAccountInfo poaAccount);
}
