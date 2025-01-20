package org.letscareer.letscareer.global.common.utils.opengraph;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.review.vo.BlogReviewOpenGraphVo;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OpenGraphUtils {
    public BlogReviewOpenGraphVo getBlogReviewOpenGraphVo(String url) {
        OpenGraph graph = null;
        try {
            graph = new OpenGraph(url, true);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return BlogReviewOpenGraphVo.of(
                graph.getContent("title"),
                graph.getContent("description"),
                graph.getContent("url"),
                graph.getContent("image")) ;
    }
}
