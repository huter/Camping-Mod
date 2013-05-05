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
    textureHeight = 64;
    
    back_part = new ModelRenderer(this, 0, 30);
    back_part.addBox(0F, 0F, 0F, 12, 14, 4);
    back_part.setRotationPoint(-3F, 0F, 0F);
    back_part.setTextureSize(64, 64);
    back_part.mirror = true;
    setRotation(back_part, 0F, 0F, 0F);
    middle_part = new ModelRenderer(this, 0, 16);
    middle_part.addBox(0F, 0F, 0F, 12, 12, 2);
    middle_part.setRotationPoint(-3F, 2F, 4F);
    middle_part.setTextureSize(64, 64);
    middle_part.mirror = true;
    setRotation(middle_part, 0F, 0F, 0F);
    front_part = new ModelRenderer(this, 0, 10);
    front_part.addBox(0F, 0F, 0F, 8, 4, 2);
    front_part.setRotationPoint(-1F, 8F, 6F);
    front_part.setTextureSize(64, 64);
    front_part.mirror = true;
    setRotation(front_part, 0F, 0F, 0F);
    left_part = new ModelRenderer(this, 12, 0);
    left_part.addBox(0F, 0F, 0F, 2, 6, 4);
    left_part.setRotationPoint(9F, 6.9F, 1F);
    left_part.setTextureSize(64, 64);
    left_part.mirror = true;
    setRotation(left_part, 0F, 0F, 0F);
    right_part = new ModelRenderer(this, 0, 0);
    right_part.addBox(0F, 0F, 0F, 2, 6, 4);
    right_part.setRotationPoint(-5F, 6.9F, 1F);
    right_part.setTextureSize(64, 64);
    right_part.mirror = true;
    setRotation(right_part, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
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
