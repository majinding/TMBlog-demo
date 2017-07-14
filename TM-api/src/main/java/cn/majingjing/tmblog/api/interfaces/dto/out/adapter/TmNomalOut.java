package cn.majingjing.tmblog.api.interfaces.dto.out.adapter;

import java.io.Serializable;

/**
 * Created by JingjingMa on 2017/06/06.
 *
 * @Email <a href="majinding888@163.com">majinding888@foxmail.com</a>
 */
public abstract class TmNomalOut<T> implements Serializable{
    protected T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
