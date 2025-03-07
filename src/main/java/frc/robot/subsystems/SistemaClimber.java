package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import javax.lang.model.util.ElementScanner14;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkClosedLoopController;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.EstadoClimber;
import frc.robot.Constants.EstadoDescerAlga;

public class SistemaClimber extends SubsystemBase {
  public SparkMax MotorClimber = new SparkMax(Constants.ConstanteSistemaClimber.ClimberMotorsID, MotorType.kBrushless);
 
  SparkMaxConfig configuracaoMotorClimber = new SparkMaxConfig();
  public EstadoClimber estadoAtual = EstadoClimber.PARADO;
  
  double velocidade = 0;

  public SistemaClimber() {
    configuracaoMotorClimber.inverted(true).idleMode(IdleMode.kBrake);
  
    MotorClimber.configure(configuracaoMotorClimber, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    MotorClimber.getEncoder().setPosition(0); //Reiniciando o valor do climber
  }

  @Override
  public void periodic() {
    if(estadoAtual == EstadoClimber.CLIMBING){
      MotorClimber.set(estadoAtual.velocidade);
    }else if(estadoAtual == EstadoClimber.RECLIMBING) {
      MotorClimber.set(estadoAtual.velocidade);
    }else{
      MotorClimber.set(EstadoClimber.PARADO.velocidade);
  }
  }

  public void DefinirEstadoAtual(double velocidade){
    this.velocidade = velocidade;
  }

  //Algumas classes só são possíveis de serem instanciadas dentro                                                                                                  
  public double ValorEncoderClimber(){
    return MotorClimber.getEncoder().getPosition();
  }

  public void DefinirEstadoMecanismo(EstadoClimber estado){
    this.estadoAtual = estado;
  }
}