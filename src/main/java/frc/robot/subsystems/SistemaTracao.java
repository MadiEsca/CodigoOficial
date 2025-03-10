package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.EstadoTracao;


public class SistemaTracao extends SubsystemBase {
  public EstadoTracao estadoAtual = EstadoTracao.PARADO;

  SparkMax motorDireitaMestre = new SparkMax(Constants.ConstantesTracao.IDmotorDireitaFrente, MotorType.kBrushed);
  SparkMax motorDireitaTras = new SparkMax(Constants.ConstantesTracao.IDmotorDiretaTras, MotorType.kBrushed);
  SparkMax motorEsquerdaMestre = new SparkMax(Constants.ConstantesTracao.IDmotorEsquerdaTras, MotorType.kBrushed);
  SparkMax motorEsquerdaTras = new SparkMax(Constants.ConstantesTracao.IDmotorEsquerdaFrente, MotorType.kBrushed);

  SparkMaxConfig configMotorDireitaMestre = new SparkMaxConfig();
  SparkMaxConfig configMotorEsquerdaMestre = new SparkMaxConfig();
  SparkMaxConfig configMotorDireita = new SparkMaxConfig();
  SparkMaxConfig configMotorEsquerda = new SparkMaxConfig();

  //MotorControllerGroup agrupamentoMotoresEsquerda = new MotorControllerGroup(motorEsquerdaTras, motorEsquerdaMestre);
  //MotorControllerGroup agrupamenoMotoresDireita = new MotorControllerGroup(motorDireitaMestre, motorDireitaTras);

  DifferentialDrive tracao = new DifferentialDrive(motorEsquerdaMestre, motorDireitaMestre);

  
  public SistemaTracao() {

    configMotorDireitaMestre
      .inverted(true)
      .idleMode(IdleMode.kBrake);

    configMotorEsquerdaMestre
      .inverted(true)
      .idleMode(IdleMode.kBrake);

    configMotorDireita
      .inverted(true)
      .idleMode(IdleMode.kBrake)
      .follow(motorDireitaMestre);

    configMotorEsquerda
      .inverted(false)
      .idleMode(IdleMode.kBrake)
      .follow(motorEsquerdaMestre);

    configMotorDireita.smartCurrentLimit(60);
    configMotorEsquerda.smartCurrentLimit(60);
  
    motorDireitaMestre.configure(configMotorDireita, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    motorDireitaTras.configure(configMotorDireita, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    motorEsquerdaTras.configure(configMotorEsquerda, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    motorEsquerdaMestre.configure(configMotorEsquerda, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }

  @Override
  public void periodic() {}

  //Configuração das formas de driver
  public void arcadeMode(double valorX, double valorY){
    tracao.arcadeDrive(valorX, valorY);
  }

  public void tankmode(double esquerda, double direita){
    tracao.tankDrive(esquerda, direita);
  }
  public void stop(){
    tracao.stopMotor(); 
  }

  public void SetCurrentState(EstadoTracao state){
    this.estadoAtual = state;
  }

  //Definir as configurações do Encoder
}