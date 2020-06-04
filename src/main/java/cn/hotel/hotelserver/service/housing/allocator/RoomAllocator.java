package cn.hotel.hotelserver.service.housing.allocator;

import java.util.ArrayList;
import java.util.List;

/**
 * 房间资源分配器（单例类）
 */
public class RoomAllocator {
    private final static RoomAllocator instance = new RoomAllocator();

    private final List<Integer> lock = new ArrayList<>();

    private RoomAllocator() {}

    /**
     * 获取锁资源
     */
    public synchronized void lock(Integer roomId) throws InterruptedException {
        // 是否有线程以占用房间id
        while (lock.contains(roomId)) {
            // 线程等待
            wait();
        }

        lock.add(roomId);
    }

    /**
     * 释放锁资源
     */
    public synchronized void unlock(Integer roomId) {
        lock.remove(roomId);
        // 唤醒所有线程
        notifyAll();
    }

    public static RoomAllocator getInstance() {
        return instance;
    }
}
