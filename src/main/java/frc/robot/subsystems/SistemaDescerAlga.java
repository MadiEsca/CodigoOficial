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

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.EstadoDescerAlga;

public class SistemaDescerAlga extends SubsystemBase {
  public SparkMax DesceAMotor = new SparkMax(Constants.ConstanteSistemaDescerAlga.DesceAMotorsIDMotorsID, MotorType.kBrushless);

  SparkMaxConfig configSparkMotor = new SparkMaxConfig();

  public EstadoDescerAlga estadoAtual = EstadoDescerAlga.PARADO;

  public SistemaDescerAlga() {
    configSparkMotor.inverted(true).idleMode(IdleMode.kBrake);
  
      DesceAMotor.configure(configSparkMotor, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }

  @Override
  public void periodic() {
    if(estadoAtual == EstadoDescerAlga.DESCE ||/*operador "OU" ||*/ estadoAtual == EstadoDescerAlga.SOBE ){
        DesceAMotor.set(estadoAtual.velocidade);
    } else{
        DesceAMotor.set(EstadoDescerAlga.PARADO.velocidade);
    }
  }

  public void DefinirEstadoMecanismo(EstadoDescerAlga estado){
    this.estadoAtual = estado;
  }
}