package frc.robot.commands.Manipulator;

import edu.wpi.first.wpilibj2.command.InstantCommand;

import frc.robot.RobotContainer;
import frc.robot.Constants.EstadoClimber;
import frc.robot.Constants.EstadoCoral;
import frc.robot.Constants.ConstantesTracao;
import frc.robot.Constants.EstadoTracao;
import frc.robot.Constants.EstadoDescerAlga;
import frc.robot.Constants.EstadoPuxarAlga;
import frc.robot.Constants.*;

public class DefinirEstadoMecanismo extends InstantCommand {
  
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
    private EstadoCoral estadoAtualCoral = EstadoCoral.PARADO;
    private EstadoTracao estadoAtualTracao = EstadoTracao.PARADO;
    private EstadoClimber estadoAtualClimber = EstadoClimber.PARADO;
    private EstadoDescerAlga estadoAtualDescerAlga = EstadoDescerAlga.PARADO;
    private EstadoPuxarAlga estadoAtualPuxarAlga = EstadoPuxarAlga.PARADO;
    
    //Métodos Enuns
    boolean currentStateCoralOnly = false;
    boolean currentStateDriveTrainOnly = false;
    boolean currentStateClimberGOnly = false;
    boolean currentStateDesceAOnly = false;
    boolean currentPosition = false;

    double velocidadeAtualAlga = 0;

    public DefinirEstadoMecanismo(EstadoCoral estado) {
      this.estadoAtualCoral = estado;
      addRequirements(RobotContainer.sistemaCoral);
      currentStateCoralOnly = true;
    }

    public DefinirEstadoMecanismo(EstadoTracao estado) {
      this.estadoAtualTracao = estado;
      addRequirements(RobotContainer.sistemaTracao);
      currentStateDriveTrainOnly = true;
    }

    public DefinirEstadoMecanismo(EstadoClimber estado) {
      this.estadoAtualClimber = estado;
      addRequirements(RobotContainer.SistemaClimber);
      currentStateClimberGOnly = true;
    }
    
    public DefinirEstadoMecanismo(EstadoDescerAlga estado) {
      this.estadoAtualDescerAlga = estado;
      addRequirements(RobotContainer.SistemaDescerAlga);
      currentStateDesceAOnly = true;
    }

    public DefinirEstadoMecanismo(EstadoPuxarAlga estado){
      this.velocidadeAtualAlga = estado.velocidade;
    }

    @Override
  public void initialize() {
    if(currentStateCoralOnly) {
      RobotContainer.sistemaCoral.DefinirEstadoMecanismo(this.estadoAtualCoral);
    } else if(currentStateDriveTrainOnly){
      RobotContainer.sistemaTracao.DefinirEstadoMecanismo(this.estadoAtualTracao);
    }else if(currentStateClimberGOnly){
      RobotContainer.SistemaClimber.DefinirEstadoMecanismo(this.estadoAtualClimber);
    
    
    }else if(currentStateDesceAOnly){
      RobotContainer.SistemaDescerAlga.DefinirEstadoMecanismo(this.estadoAtualDescerAlga);
    }else if(currentStateDesceAOnly){
      RobotContainer.SistemaPuxarAlga.DefinirEstadoMecanismo(this.estadoAtualPuxarAlga);
    }
  }
}