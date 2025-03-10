package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.PuxarAlgaEstado;

public class SistemaPuxarAlga extends SubsystemBase {

  private static final double diametroTambor = 0.05; // metros (5 cm)
  private static final double relacao = 64.0; // Redução 64:1
  private static final int ticksPerRotation = 42;
    
  private static final double convercaoMetros = (Math.PI * diametroTambor) / (relacao * ticksPerRotation);


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
    return PuxarAlgaMotor.getEncoder().getPosition()*convercaoMetros;
  }
  
  public void resetEncoder(){
    PuxarAlgaMotor.getEncoder().setPosition(0);
  }
}