package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;


public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  private RobotContainer m_robotContainer;
  
  @Override
  public void robotInit() {

    m_robotContainer = new RobotContainer();

    //Resetando o valor dos encoders
    RobotContainer.SistemaClimber.resetEncoder();
    RobotContainer.SistemaDescerAlga.resetEncoder();
    RobotContainer.SistemaPuxarAlga.resetEncoder();
    //TODO
    //RESETAR O ENCODER DA TRAÇÃO
  }

  //CONFIGURÇÃO DO AUTONOMO
  


  //RESTANTE DAS CONFIGURAÇÕES

  
  @Override
  public void robotPeriodic() {
    
    CommandScheduler.getInstance().run();
    
    //Valores do Encoder
    SmartDashboard.putNumber("Encoder Climber", RobotContainer.SistemaClimber.posicaoEncoder()*100);
    SmartDashboard.putNumber("Encoder Puxar Alga", RobotContainer.SistemaPuxarAlga.posicaoEncoder()*1000);
    SmartDashboard.putNumber("Encoder Descer Alga", RobotContainer.SistemaDescerAlga.posicaoEncoder()*1000);
    SmartDashboard.putBoolean("O limite maximo do climber nao foi atingido", RobotContainer.SistemaClimber.limiteMaxAtingido());
    SmartDashboard.putString("AVISOS!", "Quando o boolean estiver VERMELHO, NÃO SERÁ MAIS POSSÍVEL DESCER");
    SmartDashboard.putString("AVISOS!!", "SEMPRE RETRAIR OS MECANISMOS");
    SmartDashboard.putBoolean("O limite minimo do climber nao foi atingido", RobotContainer.SistemaClimber.limiteMinAtingido());
    SmartDashboard.putNumber("Tempo de Partida", DriverStation.getMatchTime());
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