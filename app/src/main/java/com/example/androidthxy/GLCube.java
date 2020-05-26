package com.example.androidthxy;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import javax.microedition.khronos.opengles.GL10;

public class GLCube {
    private final IntBuffer mVertexBuff;
    public GLCube(){
        int one = 65536;
        int half = one / 2;
        int[] vertices = {
                // 前面
                -half, -half, half, half, -half, half,
                -half, half, half, half, half, half,
                // 后面
                -half, -half, -half, half, -half, -half,
                -half, half, -half, half, half, -half,
                // 左面
                -half, -half, half, -half, half, half,
                -half, -half, -half, -half, half, -half,
                // 右面
                half, -half, half, half, half, half,
                half, -half, -half, half, half, -half,
                // 上面
                -half, half, half, half, half, half,
                -half, half, -half, half, half, -half,
                // 下面
                -half, -half, half, half, -half, half,
                -half, -half, -half, half, -half, -half
        };
        // 创建顶点坐标数据缓冲
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
        // 设置字节顺序
        vbb.order(ByteOrder.nativeOrder());
        // 将字节缓冲区转换为int型缓冲区
        mVertexBuff = vbb.asIntBuffer();
        mVertexBuff.put(vertices);
        mVertexBuff.position(0);
    }

    public void draw(GL10 gl){
        // 为画笔指定顶点坐标数据
        gl.glVertexPointer(3, GL10.GL_FIXED, 0,mVertexBuff);
        // 绘制前面和后面
        gl.glColor4f(1, 0, 0, 1);
        gl.glNormal3f(0, 0, 1);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP,0, 4);
        gl.glColor4f(1, 0, 0.5f, 1);
        gl.glNormal3f(0, 0, -1);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP,4, 4);
        // 绘制左面和右面
        gl.glColor4f(0, 1, 0, 1);
        gl.glNormal3f(-1, 0, 0);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP,8, 4);
        gl.glColor4f(0, 1, 0.5f, 1);
        gl.glNormal3f(1, 0, 0);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP,12, 4);
        // 绘制上面和下面
        gl.glColor4f(0, 0, 1, 1);
        gl.glNormal3f(0, 1, 0);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP,16, 4);
        gl.glColor4f(0, 0, 0.5f, 1);
        gl.glNormal3f(0, -1, 0);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP,20, 4);
    }


}
