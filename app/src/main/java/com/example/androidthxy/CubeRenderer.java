package com.example.androidthxy;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import java.util.WeakHashMap;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class CubeRenderer implements GLSurfaceView.Renderer {

    private GLCube cube;
    public CubeRenderer(){
        cube = new GLCube();
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        // 设置窗体的背景颜色
        gl.glClearColor(0.7f, 0.9f, 0.9f, 1.0f);
        // 启用顶点坐标数组
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        // 关闭抗抖动
        gl.glDisable(GL10.GL_DITHER);
        // 设置系统对透视进行修正
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
        // 设置阴影平滑模式
        gl.glShadeModel(GL10.GL_SMOOTH);
        // 启用深度测试
        gl.glEnable(GL10.GL_DEPTH_TEST);
        // 设置深度测试类型
        gl.glDepthFunc(GL10.GL_LEQUAL);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        // 设置OpneGL场景大小
        gl.glViewport(0, 0, width, height);
        // 计算透视视窗的宽高比
        float ratio = (float) width / height;
        // 将当前矩阵模式设置为投影矩阵
        gl.glMatrixMode(GL10.GL_PROJECTION);
        // 初始化单位矩阵
        gl.glLoadIdentity();
        // 设置透视视窗的空间大小
        GLU.gluPerspective(gl, 45.0f, ratio, 1, 100f);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        // 清除颜色缓存和深度缓存
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        // 设置使用模型矩阵进行变换
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        // 初始化单位矩阵
        gl.glLoadIdentity();
        // 当使用GL_MODELVIEW模式，必须 设置 视点，也就是观察 点
        GLU.gluLookAt(gl, 0, 0, -5, 0, 0, 0,0, 1.0f, 0.0f);
        // 旋转总坐标系
        gl.glRotatef(1000, -0.1f, -0.1f, 0.05f);
        cube.draw(gl);
    }
}


