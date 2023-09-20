package cn.bigcore.micro.outgoing;

public interface FyyPageTotalInterface {
    long getTotal(Integer pageNum, Integer pageSize);

    void remove();
}
