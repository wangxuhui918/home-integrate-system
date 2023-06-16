/**
 * 医惠科技版权所有
 */
package cn.bigcore.micro.gittools.vo;

import org.eclipse.jgit.lib.Ref;


public class FyyGitLabTag {

    private Long time;

    private String tagname;

    private Ref ref;

    private String id;

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getTagname() {
        return tagname;
    }

    public void setTagname(String tagname) {
        this.tagname = tagname;
    }

    public Ref getRef() {
        return ref;
    }

    public void setRef(Ref ref) {
        this.ref = ref;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
