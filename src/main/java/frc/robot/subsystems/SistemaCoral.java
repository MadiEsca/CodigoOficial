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
import frc.robot.Constants.EstadoCoral;

public class SistemaCoral extends SubsystemBase {
  public SparkMax coralMotor = new SparkMax(Constants.ConstanteSistemaCoral.MotorCoralID, MotorType.kBrushed);
 
  SparkMaxConfig configCoralMotor = new SparkMaxConfig();
  
  public EstadoCoral estadoAtual = EstadoCoral.PARADO;

  public SistemaCoral() {
    configCoralMotor.inverted(true).idleMode(IdleMode.kBrake);
  
    coralMotor.configure(configCoralMotor, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

  } 

  @Override
  public void periodic() {
    if(estadoAtual == EstadoCoral.ATIVADO){
      coralMotor.set(estadoAtual.velocidade);
    } else{
      coralMotor.set(-0.05);
    }
  }

  public void DefinirEstadoMecanismo(EstadoCoral state){
    this.estadoAtual = state;
  }

  public double EncoderCoral(){
    return coralMotor.getEncoder().getPosition();
  }
}
