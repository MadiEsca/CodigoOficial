package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import java.lang.Thread.State;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkClosedLoopController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.DescerAlgaEstado;
import frc.robot.Constants.EstadoClimber;


public class SistemaClimber extends SubsystemBase {
  public SparkMax ClimberMotor = new SparkMax(Constants.ConstanteSistemaClimber.SistemaClimberMotorsID, MotorType.kBrushless);
 
  SparkMaxConfig configClimberMotor = new SparkMaxConfig();
  public EstadoClimber estadoAtual = EstadoClimber.PARADO;

  public SistemaClimber() {
    configClimberMotor.inverted(true).idleMode(IdleMode.kBrake);
  
    ClimberMotor.configure(configClimberMotor, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

  } 
 
@Override
  public void periodic() {
    if(estadoAtual == EstadoClimber.CLIMBING || estadoAtual == EstadoClimber.RECLIMBING){
      ClimberMotor.set(estadoAtual.velocidade);
    } else{
      ClimberMotor.set(0);
    }
  }
  
    public void SetcurrentState(EstadoClimber state){
        this.estadoAtual = state;
    }
 
  
  }