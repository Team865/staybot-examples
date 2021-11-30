// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This is a demo program showing the use of the RobotDrive class, specifically it contains the code
 * necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  private Joystick m_joystick;
  private AddressableLED m_led;
  private AddressableLEDBuffer m_ledBuffer;

  @Override
  public void robotInit() {
    m_joystick = new Joystick(0);
    m_led = new AddressableLED(3);
    m_ledBuffer = new AddressableLEDBuffer(8);
    for (var i = 0; i < m_ledBuffer.getLength(); i++) {
      m_ledBuffer.setRGB(i, 255, 255, 255);
    }
    
    m_led.setLength(m_ledBuffer.getLength());

    m_led.setData(m_ledBuffer);
    m_led.start();
  }

  @Override
  public void teleopPeriodic() {
    double joystickX = m_joystick.getX();
    double joystickY = m_joystick.getY();

    int targetLED = (int)(7 * (0.5 + (joystickX / 2)));
    int LEDcolour = (int)(255 * (joystickY) / 2);

    int redLEDcolour = Math.abs(LEDcolour) * 2;
    int greenLEDcolour = (int)127.5 + LEDcolour;
    int blueLEDcolour = (int)127.5 - LEDcolour;
    
    for (var i = 0; i < m_ledBuffer.getLength(); i++) {
      if(i == targetLED){
        m_ledBuffer.setRGB(i, 255 - redLEDcolour, greenLEDcolour, blueLEDcolour);
      }
      else if(i == targetLED + 1 || i == targetLED - 1){
        m_ledBuffer.setRGB(i, (int)(255 - redLEDcolour / 3), (int)(greenLEDcolour / 3), (int)(blueLEDcolour / 3));
      }
      else if(i == targetLED + 2 || i == targetLED - 2){
        m_ledBuffer.setRGB(i, (int)(255 - redLEDcolour / 9), (int)(greenLEDcolour / 9), (int)(blueLEDcolour / 9));
      }
      else{
        m_ledBuffer.setRGB(i, 255, 0, 0);
      }
    }
    m_led.setData(m_ledBuffer);
  }
}
