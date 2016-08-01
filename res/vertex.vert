#version 120
//The vertex shader operates on each vertex

attribute  vec2 in_Position; 
attribute  vec3 in_Color; 

varying vec2 vertexPosition;
varying  vec3 ex_Color; 

void main() {
    
    gl_Position = vec4(in_Position.x, in_Position.y, 0.0, 1.0); 
    
    ex_Color = in_Color; 
}