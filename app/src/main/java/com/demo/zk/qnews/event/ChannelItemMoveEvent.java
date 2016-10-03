package com.demo.zk.qnews.event;

/**
 * ClassName:com.demo.zk.qnews.event
 * Author: zk<p>.
 * time: 2016/10/3 15:50.
 * Function:
 * UpdateUser: <p>
 * UpdateDate: <p>
 */

public class ChannelItemMoveEvent {
    private int fromPosition;
    private int toPosition;

    public int getFromPosition() {
        return fromPosition;
    }

    public int getToPosition() {
        return toPosition;
    }

    public ChannelItemMoveEvent(int fromPosition, int toPosition) {
        this.fromPosition = fromPosition;
        this.toPosition = toPosition;

    }
}
