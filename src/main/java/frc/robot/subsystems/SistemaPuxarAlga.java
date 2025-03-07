package frc.robot.subsystems;

import static edu.wpi.first.units.Units.Newton;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import frc.robot.Constants;
import frc.robot.Constants.EstadoCoral;
import frc.robot.Constants.EstadoDescerAlga;
import frc.robot.Constants.EstadoPuxarAlga;
import frc.robot.Constants.EstadoTracao;

public class SistemaPuxarAlga {
    //Instaciar os motores

    //Instanciar os objetos de configuração dos motores

    //Instanciar os estados dos motores -> objetos enum
    //Criar uma lógica para definir os estados com base nos valores recebidos dos

    SparkMax motorPuxarAlga = new SparkMax(Constants.ConstanteSistemaPuxarAlga.SistemaPuxarAlgaID, MotorType.kBrushed);
    SparkMaxConfig configuracaoMotorPuxarAlga = new SparkMaxConfig();

    public EstadoPuxarAlga estadoAtual = EstadoPuxarAlga.PARADO;
    
    public SistemaPuxarAlga(){
        configuracaoMotorPuxarAlga.smartCurrentLimit(60).idleMode(IdleMode.kBrake);
    }
    
    public void DefinirEstadoMecanismo(EstadoPuxarAlga estado){
        this.estadoAtual = estado;
    }

}