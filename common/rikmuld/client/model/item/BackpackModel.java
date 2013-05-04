package rikmuld.client.model.item;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class BackpackModel extends ModelBase
{
    ModelRenderer back_part;
    ModelRenderer middle_part;
    ModelRenderer front_part;
    ModelRenderer left_part;
    ModelRenderer right_part;
  
  public BackpackModel()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      back_part = new ModelRenderer(this, 0, 0);
      back_part.addBox(0F, 0F, 0F, 6, 7, 2);
      back_part.setRotationPoint(0F, 0F, 0F);
      back_part.setTextureSize(64, 32);
      back_part.mirror = true;
      setRotation(back_part, 0F, 0F, 0F);
      middle_part = new ModelRenderer(this, 0, 0);
      middle_part.addBox(0F, 0F, 0F, 6, 6, 1);
      middle_part.setRotationPoint(0F, 1F, 2F);
      middle_part.setTextureSize(64, 32);
      middle_part.mirror = true;
      setRotation(middle_part, 0F, 0F, 0F);
      front_part = new ModelRenderer(this, 0, 0);
      front_part.addBox(0F, 0F, 0F, 4, 2, 1);
      front_part.setRotationPoint(1F, 4F, 3F);
      front_part.setTextureSize(64, 32);
      front_part.mirror = true;
      setRotation(front_part, 0F, 0F, 0F);
      left_part = new ModelRenderer(this, 0, 0);
      left_part.addBox(0F, 0F, 0F, 1, 3, 2);
      left_part.setRotationPoint(6F, 3.333333F, 0.5F);
      left_part.setTextureSize(64, 32);
      left_part.mirror = true;
      setRotation(left_part, 0F, 0F, 0F);
      right_part = new ModelRenderer(this, 0, 0);
      right_part.addBox(0F, 0F, 0F, 1, 3, 2);
      right_part.setRotationPoint(-1F, 3.333333F, 0.5F);
      right_part.setTextureSize(64, 32);
      right_part.mirror = true;
      setRotation(right_part, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    setRotationAngles(f, f1, f2, f3, f4, f5, entity); 
    
    back_part.renderWithRotation(f5);
    middle_part.renderWithRotation(f5);
    front_part.renderWithRotation(f5);
    left_part.renderWithRotation(f5);
    right_part.renderWithRotation(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity ent)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, ent);
    back_part.render(f5);
    middle_part.render(f5);
    front_part.render(f5);
    left_part.render(f5);
    right_part.render(f5);
  }
}
