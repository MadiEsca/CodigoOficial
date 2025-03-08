package frc.robot;

import frc.robot.Constants.EstadoClimber;
import frc.robot.Constants.EstadoCoral;
import frc.robot.Constants.EstadoTracao;
import frc.robot.Constants.PuxarAlgaEstado;
//import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.TracaoControlada;
//import frc.robot.commands.ClimberJoy;
import edu.wpi.first.wpilibj.RobotBase;
import frc.robot.subsystems.SistemaPuxarAlga;
import frc.robot.commands.Manipulator.DefinirEstadoMecanismo;
import frc.robot.subsystems.SistemaTracao;
import frc.robot.subsystems.SistemaClimber;
import frc.robot.subsystems.SistemaCoral;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.SistemaDescerAlga;
import frc.robot.Constants.DescerAlgaEstado; 


  public class RobotContainer {
  public static final SistemaTracao sistemaTracao = new SistemaTracao();
  public static final SistemaCoral sistemaCoral = new SistemaCoral();
  public static final SistemaDescerAlga SistemaDescerAlga = new SistemaDescerAlga();
  public static final SistemaPuxarAlga SistemaPuxarAlga = new SistemaPuxarAlga();
  public static final SistemaClimber SistemaClimber = new SistemaClimber();


    CommandXboxController joystick1 = new CommandXboxController(0);
    CommandXboxController joystick2 = new CommandXboxController(1);
  
    public RobotContainer() {
    configureBindings();
    defaultcommands();
    }
    
    private void configureBindings() {
    
    //Controle do driver da tração
    joystick1.button(1).whileTrue(new DefinirEstadoMecanismo(EstadoTracao.PARADO));
    joystick1.button(5).whileTrue(new DefinirEstadoMecanismo(EstadoTracao.MID));
    joystick1.button(6).whileTrue(new DefinirEstadoMecanismo(EstadoTracao.FULL));
    

    //Climber
    joystick2.leftTrigger().whileTrue(new DefinirEstadoMecanismo(EstadoClimber.RECLIMBING)).onFalse(new DefinirEstadoMecanismo(EstadoClimber.PARADO));
    joystick2.button(5).whileTrue(new DefinirEstadoMecanismo(EstadoClimber.CLIMBING)).onFalse(new DefinirEstadoMecanismo(EstadoClimber.PARADO));
    //Braço\alga
    joystick2.rightTrigger().whileTrue(new DefinirEstadoMecanismo(DescerAlgaEstado.SOBE)).onFalse(new DefinirEstadoMecanismo(DescerAlgaEstado.PARADO));
    joystick2.button(6).whileTrue(new DefinirEstadoMecanismo(DescerAlgaEstado.DESCE)).onFalse(new DefinirEstadoMecanismo(DescerAlgaEstado.PARADO));
    //Coral
    joystick2.button(4).whileTrue(new DefinirEstadoMecanismo(EstadoCoral.ATIVADO)).onFalse(new DefinirEstadoMecanismo(EstadoCoral.PARADO));
    joystick2.button(2).whileTrue(new DefinirEstadoMecanismo(EstadoCoral.CONTRARIO)).onFalse(new DefinirEstadoMecanismo(EstadoCoral.PARADO));
    //Puxar Alga
    joystick2.button(3).whileTrue(new DefinirEstadoMecanismo(PuxarAlgaEstado.PUXA)).onFalse(new DefinirEstadoMecanismo(PuxarAlgaEstado.PARADO));
    joystick2.button(1).whileTrue(new DefinirEstadoMecanismo(PuxarAlgaEstado.SOLTA)).onFalse(new DefinirEstadoMecanismo(PuxarAlgaEstado.PARADO));
    // CABO
  }
    private void defaultcommands(){
    sistemaTracao.setDefaultCommand(new TracaoControlada(sistemaTracao, joystick1));
  }
}