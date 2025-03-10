package frc.robot.subsystems;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.config.RobotConfig;
import com.pathplanner.lib.controllers.PPLTVController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.EstadoTracao;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import com.pathplanner.lib.*;


public class SistemaTracao extends SubsystemBase {
  //OBJETO DE CONFIGURAÇÃO DO AUTONOMO
  // RobotConfig config;
  
  public EstadoTracao estadoAtual = EstadoTracao.PARADO;

  SparkMax motorDireitaFrente = new SparkMax(Constants.ConstantesTracao.IDmotorDireitaFrente, MotorType.kBrushed);
  SparkMax motorDireitaTras = new SparkMax(Constants.ConstantesTracao.IDmotorDiretaTras, MotorType.kBrushed);
  SparkMax motorEsquerdaFrente = new SparkMax(Constants.ConstantesTracao.IDmotorEsquerdaTras, MotorType.kBrushed);
  SparkMax motorEsquerdaTras = new SparkMax(Constants.ConstantesTracao.IDmotorEsquerdaFrente, MotorType.kBrushed);

  // SparkMaxConfig configMotorDireitaMestre = new SparkMaxConfig();
  // SparkMaxConfig configMotorEsquerdaMestre = new SparkMaxConfig();
  SparkMaxConfig configMotorDireita = new SparkMaxConfig();
  SparkMaxConfig configMotorEsquerda = new SparkMaxConfig();


  MotorControllerGroup agrupamentoMotoresEsquerda = new MotorControllerGroup(motorEsquerdaTras, motorEsquerdaFrente);
  MotorControllerGroup agrupamenoMotoresDireita = new MotorControllerGroup(motorDireitaFrente, motorDireitaTras);

  DifferentialDrive tracao = new DifferentialDrive(motorEsquerdaFrente, motorDireitaFrente);
    
  public SistemaTracao() {
    //CRIAÇÃO DO SISTEMA AUTONOMO
    // try{
    //   config = RobotConfig.fromGUISettings();
      
    // } catch (Exception e) {
    //   // Handle exception as needed
    //   e.printStackTrace();
    // }

    //CONFIGURAÇÃO DOS MOTORES
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
    motorEsquerdaTras.configure(configMotorEsquerda, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    motorEsquerdaFrente.configure(configMotorEsquerda, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
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





  //CONFIGURAÇÃO AUTONOMO
  //AQUI CONFIGURAMOS O ROBOT BUILDER

  
}