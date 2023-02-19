package ru.netology.sboot_task1.profile;

public class ProductionProfile implements SystemProfile {
  @Override
  public String getProfile() {
    return "Current profile is production";
  }
}