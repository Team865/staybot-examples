// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.TimedRobot;

/**
 * This is a demo program showing how to control a survomotor with
 * a joystick using the roboRIO.
 * To run the program, deploy it to the roboRIO then open your DriverStation.
 * Press the enable button, then move your joystick left and right.
 */
public class Robot extends TimedRobot {
  //Setting up our variables.
  private Servo m_servo;
  private Joystick m_joystick;

  @Override
  public void robotInit() {
    //This creates a new servomotor class. The input number refers to what port the motor is plugged into.
    m_servo = new Servo(2);
  /**
    * As you'd expect, a Joystick class creates one Joystick.
    * If the program isn't working, make sure that your DriverStation recognised your Joystick.
    * You can use the rescan button under the USB Connections tab if your DriverStation doesn't
    * recognise your controller.
    */
    m_joystick = new Joystick(0);
  }

  //This code runs when the robot is in teleop mode.
  @Override
  public void teleopPeriodic() {
  /**
    * The servo's .set takes a double from 0.0 - 1.0 and turns the motor to that location.
    * The joystick's .getX returns a double from -1.0 - 1.0. Value -1.0 is the joystick
    * pushed all the way to the left and value 1.0 is the joystick pushed all the way to the right.
    * We adjust the value to match the servo's .set function as a result.
    */
    m_servo.set(0.5 - (m_joystick.getX()/2));
  }
}
