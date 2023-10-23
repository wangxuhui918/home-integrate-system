package cn.bigcore.micro.outgoing.page;

public interface FyyPageTotalInterface {
    long getTotal(Integer pageNum, Integer pageSize);

    void remove();
}
