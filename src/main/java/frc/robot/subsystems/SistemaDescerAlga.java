package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkClosedLoopController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.DescerAlgaEstado;
import frc.robot.Constants.LimiteEncoders;

public class SistemaDescerAlga extends SubsystemBase {
  public SparkMax DesceAlgaMotor = new SparkMax(Constants.ConstanteSistemaDescerAlga.DesceAlgaMotorsID, MotorType.kBrushless);
 
  SparkMaxConfig configSparkMotor = new SparkMaxConfig();

  public DescerAlgaEstado estadoAtual = DescerAlgaEstado.PARADO;

  public SistemaDescerAlga() {
    configSparkMotor.inverted(true).idleMode(IdleMode.kBrake);
  
      DesceAlgaMotor.configure(configSparkMotor, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  } 

  @Override
  public void periodic() {
    if(estadoAtual == DescerAlgaEstado.DESCE ||/*operador "OU" ||*/ estadoAtual == DescerAlgaEstado.SOBE ){
        DesceAlgaMotor.set(estadoAtual.velocidade);
    } else{
        DesceAlgaMotor.set(0);
    }
  }

  public void SetCurrentState(DescerAlgaEstado estado){
    this.estadoAtual = estado;
  }

  //Configuração do Encoder
  public double posicaoEncoder(){
    return DesceAlgaMotor.getEncoder().getPosition();
  }

  public void resetEncoder(){
    DesceAlgaMotor.getEncoder().setPosition(0);
  }
  
  public boolean limiteMaxAtingido(){
    if (posicaoEncoder() > LimiteEncoders.limiteMaxClimber){
      return false;
    } else{
      return true;
    }
  }
  
  public boolean limiteMinAtingido(){
    if (posicaoEncoder() < LimiteEncoders.limiteMinimo){
      return false;
    } else{
      return true;
    }
  }
}