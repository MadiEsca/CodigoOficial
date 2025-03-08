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

public class SistemaDescerAlga extends SubsystemBase {
  public SparkMax DesceAMotor = new SparkMax(Constants.ConstanteSistemaDescerAlga.DesceAlgaMotorsID, MotorType.kBrushless);
 
  SparkMaxConfig configSparkMotor = new SparkMaxConfig();

  public DescerAlgaEstado estadoAtual = DescerAlgaEstado.PARADO;

  public SistemaDescerAlga() {
    configSparkMotor.inverted(true).idleMode(IdleMode.kBrake);
  
      DesceAMotor.configure(configSparkMotor, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  } 

  @Override
  public void periodic() {
    if(estadoAtual == DescerAlgaEstado.DESCE ||/*operador "OU" ||*/ estadoAtual == DescerAlgaEstado.SOBE ){
        DesceAMotor.set(estadoAtual.velocidade);
    } else{
        DesceAMotor.set(0);
    }
  }

  public void SetCurrentState(DescerAlgaEstado estado){
    this.estadoAtual = estado;
  }
}