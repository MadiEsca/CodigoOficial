package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.SistemaTracao;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class ControlarTracao extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  double valorX;
  double valorY;
  private final SistemaTracao sistemaTracao;
  private final CommandXboxController joystick1;
  
  public ControlarTracao(SistemaTracao sistemaTracao, CommandXboxController joy1) {
    this.sistemaTracao = sistemaTracao;
    this.joystick1 = joy1;

    addRequirements(sistemaTracao);
  }

  @Override
  public void initialize()
   {
    valorX = 0;
    valorY = 0;
  } 

  @Override
  public void execute() {
    valorX = joystick1.getRawAxis(Constants.JoysticsDeControle.valorX) * sistemaTracao.estadoAtual.velocidade;
    valorY = joystick1.getRawAxis(Constants.JoysticsDeControle.valorY) * sistemaTracao.estadoAtual.velocidade;

    sistemaTracao.arcadeMode(valorX, valorY);
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}