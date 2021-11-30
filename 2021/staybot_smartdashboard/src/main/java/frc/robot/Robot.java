// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.Random;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This is a demo program showing the use of the SmartDashboard to
 * display information from within the code. Constantly updating information is
 * displayed on the SmartDashboard. The SmartDashboard can be used to
 * bugtest your code.
 */
public class Robot extends TimedRobot {
  private Joystick m_joystick;
  private Random m_random;
  private double m_random_double;
  private int count;

  @Override
  public void robotInit() {
    m_joystick = new Joystick(0);
    m_random = new Random();
    m_random_double = m_random.nextDouble();
    count = 0;
  }

  @Override
  public void teleopPeriodic() {
    //Update the SmartDashboard info
    SmartDashboard.putNumber("Joystick X", m_joystick.getX());
    SmartDashboard.putNumber("Joystick Y", m_joystick.getY());
    SmartDashboard.putNumber("Random Number", m_random_double);
    SmartDashboard.putNumber("Percent", (int)(count/5));
    SmartDashboard.putNumber("Raw Count", count);
    
    //Update the count
    count += 1;
    if(count > 505){
      m_random_double = m_random.nextDouble();
      count = 0;
    }
  }
}
