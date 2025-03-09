package frc.robot.commands.Manipulator;

import edu.wpi.first.wpilibj2.command.InstantCommand;

import frc.robot.RobotContainer;
import frc.robot.Constants.EstadoClimber;
import frc.robot.Constants.EstadoCoral;
import frc.robot.Constants.ConstantesTracao;
import frc.robot.Constants.EstadoTracao;
import frc.robot.Constants.PuxarAlgaEstado;
import frc.robot.Constants.DescerAlgaEstado;
import frc.robot.Constants.ConstanteSistemaClimber;
import frc.robot.Constants.ConstanteSistemaDescerAlga;

public class DefinirEstadoMecanismo extends InstantCommand {
  
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
      private EstadoCoral currentStateCoral = EstadoCoral.PARADO;
      private EstadoTracao currentStateDriveTrain = EstadoTracao.PARADO;
      private DescerAlgaEstado currentStateDesceA = DescerAlgaEstado.PARADO;
      private EstadoClimber currentStateClimber = EstadoClimber.PARADO;
      private PuxarAlgaEstado currentStatePuxarA = PuxarAlgaEstado.PARADO;

    boolean currentStateCoralOnly = false;
    boolean currentStateDriveTrainOnly = false;
    boolean currentStateDesceAOnly = false;
    boolean currentStatePuxaAOnly = false;
    boolean currentStateClimberOnly = false;

  public DefinirEstadoMecanismo(EstadoCoral estado) {
    this.currentStateCoral = estado;
    addRequirements(RobotContainer.sistemaCoral);
    currentStateCoralOnly = true;
  }
  
  public DefinirEstadoMecanismo(EstadoTracao estado) {
    this.currentStateDriveTrain = estado;
    addRequirements(RobotContainer.sistemaTracao);
    currentStateDriveTrainOnly = true;
  }
  
  
  public DefinirEstadoMecanismo(DescerAlgaEstado estado) {
    this.currentStateDesceA = estado;
    addRequirements(RobotContainer.SistemaDescerAlga);
    currentStateDesceAOnly = true;
  }
  
  public DefinirEstadoMecanismo(PuxarAlgaEstado estado) {
    this.currentStatePuxarA = estado;
    addRequirements(RobotContainer.SistemaPuxarAlga);
    currentStatePuxaAOnly = true;
            
    }
  public DefinirEstadoMecanismo(EstadoClimber estado) {
    this.currentStateClimber = estado;
    addRequirements(RobotContainer.SistemaClimber);
    currentStateClimberOnly = true;

}
            
    
  @Override
  public void initialize() {
    if(currentStateCoralOnly) {
      RobotContainer.sistemaCoral.DefinirEstadoAtualMecanismo(this.currentStateCoral);
    } else if(currentStateDriveTrainOnly){
      RobotContainer.sistemaTracao.SetCurrentState(this.currentStateDriveTrain);
    }else if(currentStateDesceAOnly){
      RobotContainer.SistemaDescerAlga.SetCurrentState(this.currentStateDesceA);
    }else if(currentStateClimberOnly) {
      RobotContainer.SistemaClimber.SetcurrentState(this.currentStateClimber);
    }else if(currentStatePuxaAOnly){
      RobotContainer.SistemaPuxarAlga.SetCurrentState(this.currentStatePuxarA);
    }
  } 
}