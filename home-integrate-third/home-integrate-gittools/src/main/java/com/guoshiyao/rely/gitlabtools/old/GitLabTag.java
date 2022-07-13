


/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 郭诗瑶 18671020380@163.com
 *
 */

package com.guoshiyao.rely.gitlabtools.old;

import lombok.Data;
import org.eclipse.jgit.lib.Ref;

@Data
public class GitLabTag {

    private Long time;

    private String tagname;

    private Ref ref;

    private String id;

}
