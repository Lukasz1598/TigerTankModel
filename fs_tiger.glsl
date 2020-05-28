uniform sampler2D texture;

void main(void){
  vec4 color=texture2D(texture,gl_TexCoord[0].st);
  if(color.r<0.15 && color.g<0.15 && color.b<0.15)
    color.a=0.0;
  gl_FragColor=color;
}