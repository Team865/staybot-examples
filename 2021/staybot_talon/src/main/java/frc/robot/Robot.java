// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import com.ctre.phoenix.motorcontroller.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;

/**
 * This is a demo program showing the use of the RobotDrive class, specifically it contains the code
 * necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  private TalonSRX m_talon_fx;
  DigitalInput left_button;
  DigitalInput right_button;

  @Override
  public void robotInit() {
    m_talon_fx = new TalonSRX(14);
    left_button = new DigitalInput(0);
    right_button = new DigitalInput(1);
  }

  @Override
  public void teleopPeriodic() {
    boolean new_left_button = !left_button.get();
    boolean new_right_button = !right_button.get();

    double new_speed = 0.0;
    if(new_left_button && !new_right_button){
      new_speed = 0.1;
    }
    else if (new_right_button && !new_left_button){
      new_speed = -0.1;
    }
    m_talon_fx.set(TalonSRXControlMode.PercentOutput, new_speed);
    SmartDashboard.putBoolean("Is alive", true) ;
    SmartDashboard.putNumber("New speed", new_speed);
  }
}
