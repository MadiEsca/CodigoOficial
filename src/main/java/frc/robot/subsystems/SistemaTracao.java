package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.ctre.phoenix6.hardware.Pigeon2;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkClosedLoopController;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.EstadoCoral;
import frc.robot.Constants.EstadoTracao;

public class SistemaTracao extends SubsystemBase {
  public EstadoTracao estadoAtual = EstadoTracao.PARADO;

  SparkMax motorDireitaFrente = new SparkMax(Constants.ConstantesTracao.IDmotorDireitaFrente, MotorType.kBrushed);
  SparkMax motorDireitaTras = new SparkMax(Constants.ConstantesTracao.IDmotorDiretaTras, MotorType.kBrushed);
  SparkMax motorEsquerdaTras = new SparkMax(Constants.ConstantesTracao.IDmotorEsquerdaTras, MotorType.kBrushed);
  SparkMax motorEsquerdaFrente = new SparkMax(Constants.ConstantesTracao.IDmotorEsquerdaFrente, MotorType.kBrushed);

  SparkMaxConfig configMotorDireita = new SparkMaxConfig();
  SparkMaxConfig configMotorEsquerda = new SparkMaxConfig();

  MotorControllerGroup agrupamentoMotoresEsquerda = new MotorControllerGroup(motorEsquerdaFrente, motorEsquerdaTras);
  MotorControllerGroup agrupamenoMotoresDireita = new MotorControllerGroup(motorDireitaFrente, motorDireitaTras);

  DifferentialDrive tracao = new DifferentialDrive(agrupamentoMotoresEsquerda, agrupamenoMotoresDireita);

  public SistemaTracao() {

    configMotorDireita
      .inverted(true)
      .idleMode(IdleMode.kBrake);

    configMotorEsquerda
      .inverted(false)
      .idleMode(IdleMode.kBrake);

    configMotorDireita.smartCurrentLimit(60);
    configMotorEsquerda.smartCurrentLimit(60);
  
    motorDireitaFrente.configure(configMotorDireita, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    motorDireitaTras.configure(configMotorDireita, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    motorEsquerdaFrente.configure(configMotorEsquerda, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    motorEsquerdaTras.configure(configMotorEsquerda, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }

  @Override
  public void periodic() {}

  public void arcadeMode(double valorX, double valorY){
    tracao.arcadeDrive(valorX, valorY);
  }

  public void tankmode(double esquerda, double direita){
    tracao.tankDrive(esquerda, direita);
  }
  public void stop(){
    tracao.stopMotor(); 
  }

  public void DefinirEstadoMecanismo(EstadoTracao state){
    this.estadoAtual = state;
  } 
}