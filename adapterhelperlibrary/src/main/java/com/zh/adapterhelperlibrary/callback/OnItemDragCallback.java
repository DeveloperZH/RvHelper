package com.zh.adapterhelperlibrary.callback;

/**
 * @describe: ${todo}
 * @author: Z H
 * @date: 2018/7/25 17:27
 * @pkgName: com.zh.adapterhelperlibrary.callback
 * @version: ${version}
 */
public interface OnItemDragCallback {

    /**
     * item拖拽之后数据的变化
     */
    void onItemDateChange(int fromPos, int toPos);

    /**
     * item 滑动删除
     */
    void onItemSwipedDelete(int position);
}
