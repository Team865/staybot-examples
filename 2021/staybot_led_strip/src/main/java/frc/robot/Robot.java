// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;

/**
 * This is a demo program showing off the strip of LED lights and how you can
 * control and change what each light along the strip displayed.
 */
public class Robot extends TimedRobot {
  private Joystick m_joystick;
  private AddressableLED m_led;
  private AddressableLEDBuffer m_ledBuffer;

  @Override
  public void robotInit() {
    m_joystick = new Joystick(0);
    //The input for the AddressableLED class is based on its port
    m_led = new AddressableLED(3);
    //The input for the AddressableLEDBuffer is the length of the LED strip. We have 8 lights on ours, so we input 8.
    m_ledBuffer = new AddressableLEDBuffer(8);

    //This sets the initial colour of each of the LEDs. They are all set to red here.
    for (var i = 0; i < m_ledBuffer.getLength(); i++) {
      m_ledBuffer.setRGB(i, 225, 0, 0);
    }
    
    //This sets the length of the AddressableLED to the same value that we set the buffer to.
    m_led.setLength(m_ledBuffer.getLength());

    //setData updates the value of the LED strip to whatever you put in the buffer.
    m_led.setData(m_ledBuffer);
    //start causes the LEDs to be updated every second.
    m_led.start();
  }

  @Override
  public void teleopPeriodic() {
    //Grab the position of the joystick
    double joystickX = m_joystick.getX();
    double joystickY = m_joystick.getY();

    //The x value of the joystick corisponds to what LED we change the colour of. This sets the value from 1-8.
    int targetLED = (int)(7 * (0.5 + (joystickX / 2)));
    //The y value of the joystick corisponds to what colour the LED is changed to. This sets the value from -127.5 - 127.5.
    int LEDcolour = (int)(255 * (joystickY) / 2);

    //Sets each of the colours. Note that the redLEDcolour 
    int redLEDcolour = Math.abs(LEDcolour) * 2;
    int greenLEDcolour = (int)127.5 + LEDcolour;
    int blueLEDcolour = (int)127.5 - LEDcolour;
    
  /**
    * Sets each of the lights on the strip to a diffrent colour.
    * A light designated by the x value of the joystick is the target light.
    * Lights nearby the target light also change colour.
    * The if statements detect this and adjust the light accordingly.
    * The default position for the lights when not modified is red.
    * setRGB takes the LED on the strip to change, 
    * followed by the RGB value you want to set the light to.
    */ 
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
    //setData again to update the strip.
    m_led.setData(m_ledBuffer);
  }
}
