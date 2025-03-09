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
import frc.robot.Constants.DescerAlgaEstado;
import frc.robot.Constants.EstadoClimber;
import frc.robot.Constants.EstadoCoral;
import frc.robot.Constants.PuxarAlgaEstado;

public class SistemaPuxarAlga extends SubsystemBase {
  public SparkMax PuxarAlgaMotor = new SparkMax(Constants.ConstanteSistemaPuxarAlga.SistemaPuxarAlgaMotorsID, MotorType.kBrushless);
 
  SparkMaxConfig configuracaoMotorSistemaPuxarAlga = new SparkMaxConfig();
  public PuxarAlgaEstado estadoAtual = PuxarAlgaEstado.PARADO;


  public SistemaPuxarAlga() {
    configuracaoMotorSistemaPuxarAlga.inverted(true).idleMode(IdleMode.kBrake);
  }

  @Override
  public void periodic() {
    if(estadoAtual == PuxarAlgaEstado.PUXA ||/*operador "OU" ||*/ estadoAtual == PuxarAlgaEstado.SOLTA ){
      PuxarAlgaMotor.set(estadoAtual.velocidade);
    }else{
      PuxarAlgaMotor.set(PuxarAlgaEstado.PARADO.velocidade);
    }
  
    ///////////////////////////////////////////////////////////////////////////////////
    //if(currentState == ClimberGState.CLIMBING && EncoderClimber() < 30.0 ){        //
    //  ClimberGMotor.set(currentState.speed);                                       //
    //} else if(currentState == ClimberGState.RECLIMBING && EncoderClimber() >= 0) { //
    //  ClimberGMotor.set(currentState.speed);                                       //
    //} else{                                                                        //
    //  ClimberGMotor.set(ClimberGState.STOPPED.speed);                              //
    //}                                                                              //
    ///////////////////////////////////////////////////////////////////////////////////
  }
  
  public void SetCurrentState(PuxarAlgaEstado estado){
    this.estadoAtual = estado;
  }

  //Configuração do Encoder
  public double posicaoEncoder(){
    return PuxarAlgaMotor.getEncoder().getPosition();
  }
  
  public void resetEncoder(){
    PuxarAlgaMotor.getEncoder().setPosition(0);
  }
}