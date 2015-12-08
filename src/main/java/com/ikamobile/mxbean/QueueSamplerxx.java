package com.ikamobile.mxbean;

import javax.management.MXBean;

@MXBean
public interface QueueSamplerxx {
    public QueueSample getQueueSample(); 
    public void clearQueue(); 
} 
