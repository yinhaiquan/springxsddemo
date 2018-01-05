package aop;

import annotation.NotNull;
import annotation.ValidBase;
import annotation.Validation;

import java.io.Serializable;

/**
 * @title :
 * @describle :
 * <p>
 * Create By yinhaiquan
 * @date 2018/1/2 9:41 星期二
 */
public class Pager extends ValidBase implements Serializable{
    private static final long serialVersionUID = -7575499887590687652L;

    @NotNull
    public int pageNo;
    @NotNull
    public int pageSize;

    public Pager() {
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "Pager{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                '}';
    }
}
