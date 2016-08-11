#version 120
//The fragment shader operates on each pixel in a given polygon



varying  vec3 ex_Color; 

void main() {
    gl_FragColor = vec4(ex_Color,1.0) ; 

}