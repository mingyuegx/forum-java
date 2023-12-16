package pub.developers.forum.api.request.article;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Qiangqiang.Bian
 * @create 2020/12/9
 * @desc
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleAdminPageRequest implements Serializable {

    @ApiModelProperty(value = "类型id")
    private Long typeId;

    @ApiModelProperty(value = "文章状态")
    private String auditState;

    private Boolean official;

    @ApiModelProperty("是否置顶")
    private Boolean top;

    private Boolean marrow;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("经度")
    private BigDecimal longitude;

    @ApiModelProperty("纬度")
    private BigDecimal latitude;

    @ApiModelProperty("半径")
    private BigDecimal radius;

}
