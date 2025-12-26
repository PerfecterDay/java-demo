package com.gtja.gjyw.panama;

import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.Linker;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SymbolLookup;
import java.lang.invoke.MethodHandle;
import java.nio.file.Path;

/**
 * Author: zhongzhu.wang
 * Date:2025/12/26 16:51
 */


public class HelloPanama {

    public static void main(String[] args) throws Throwable {

        // 1. 加载 native library
        System.load(Path.of("./libhello.dylib").toAbsolutePath().toString());
        // Linux 用 libhello.so

        // 2. 获取链接器
        Linker linker = Linker.nativeLinker();

        // 3. 查找 native 符号
        SymbolLookup lookup = SymbolLookup.loaderLookup();
        MemorySegment helloFunc = lookup.find("hello").orElseThrow();

        // 4. 创建函数描述（void hello()）
        FunctionDescriptor fd = FunctionDescriptor.ofVoid();

        // 5. 生成 MethodHandle
        MethodHandle hello =
                linker.downcallHandle(
                        helloFunc,
                        fd
                );

        // 6. 调用
        hello.invokeExact();
    }
}