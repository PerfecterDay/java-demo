package spring.validation;


import jakarta.validation.constraints.Size;

/**
 * Author: zhongzhu.wang
 * Date:2026/2/6 16:44
 */
record Person(@Size(min = 1, max = 10) String name) {
}


