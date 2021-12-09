// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Servo;

/**
 * This is a demo program showing the use of two digital input buttons
 * to move the servo motor left and right.
 */
public class Robot extends TimedRobot {

  DigitalInput left_button;
  DigitalInput right_button;
  Servo servo_motor;

  @Override
  public void robotInit() {
    //The inputs for all 3 of these hardwear components is the port they're plugged into.
    left_button = new DigitalInput(0);
    right_button = new DigitalInput(1);
    servo_motor = new Servo(2);
  }

  @Override
  public void teleopPeriodic() {
    //The button returns true when not pressed, and false when pressed. I switch them here for convinience.
    boolean left_button_pressed = !left_button.get();
    boolean right_button_pressed = !right_button.get();

    //Display information on the dashboard
    SmartDashboard.putBoolean("Left Button Pressed?", left_button_pressed);
    SmartDashboard.putBoolean("Right Button Pressed?", right_button_pressed);

   /**
    * If only the right button is being pressed, turn the motor counterclockwise
    * If only the left button is being pressed, turn the motor clockwise
    * Otherwise bring the motor back to its default location
    */
    double next_servo_location = 0.5;
    if(left_button_pressed && !right_button_pressed){
      next_servo_location = 1.0;
    }
    else if (right_button_pressed && !left_button_pressed){
      next_servo_location = 0.0;
    }
    servo_motor.set(next_servo_location);
  }
}
