package frc.robot;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.Subsystem;


public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  private RobotContainer m_robotContainer;
  
  @Override
  public void robotInit() {

    m_robotContainer = new RobotContainer();
  }

  
  @Override
  public void robotPeriodic() {
    
    CommandScheduler.getInstance().run();
    
    //Mostra o valor atual da corrente que está sendo passada
    SmartDashboard.putNumber("Posição Encoderl", RobotContainer.SistemaPuxarAlga.PuxarAlgaMotor.getEncoder().getPosition());
    
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void autonomousInit() {
   
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {
    
  }

  @Override
  public void teleopInit() {

    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {}

  @Override
  public void testInit() {

    CommandScheduler.getInstance().cancelAll();
  } 

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}